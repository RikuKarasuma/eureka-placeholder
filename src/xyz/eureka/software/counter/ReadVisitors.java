package xyz.eureka.software.counter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

import xyz.eureka.software.manager.DeploymentGlobals;

public final class ReadVisitors
{
	
	/**
	 * Int for storing visitors.
	 */
	private int readIn = 0;
	
	/**
	 * Reads in the number of visitors and sets to instance variable. 
	 */
	public ReadVisitors() 
	{
		readIn = read();
	}
	
	/**
	 * Returns the number of visitors read in.
	 * @return int - page visits.
	 */
	public int getVisitors()
	{
		return this.readIn;
	}
	
	/**
	 * Reads in the number of visitors from a config file located in home.
	 * @return int - page visits read from file.
	 */
	private static int read()
	{
		RandomAccessFile file = null;
		FileChannel channel = null;
		FileLock lock = null;
		int read = 0;
		boolean retry = true;
		
		while(retry)
			try
			{
				// Create paths.
				String file_path = DeploymentGlobals.getVisitorFilePath();
				
				// Get file instance.
				File locked_stats_file = new File(file_path);
				
				// Access file.
				file = new RandomAccessFile(locked_stats_file, "rw");
				// Get channel from file.
				channel = file.getChannel();
				
				lock = channel.lock();
				
				retry = false;
				
				final int int_length = 4;
				ByteBuffer in_buffer = ByteBuffer.allocate(int_length);
				channel.read(in_buffer);
				read = in_buffer.getInt(0);
			}
			catch(OverlappingFileLockException e)
			{
				retry = true;
				waitFor();
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					// Release lock.
					if(lock != null && lock.isValid())
						lock.release();
						
					// Close file.
					if(file != null)
						file.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		
		return read;
	}
	
	/**
	 * Wait for 100ms if the file channel is overlapped.
	 */
	private static void waitFor()
	{
		synchronized (Thread.currentThread())
		{
			try
			{
				Thread.currentThread().wait(100);
			}
			catch(InterruptedException e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
