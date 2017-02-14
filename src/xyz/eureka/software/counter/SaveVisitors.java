package xyz.eureka.software.counter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

import xyz.eureka.software.manager.DeploymentGlobals;

public final class SaveVisitors implements Runnable
{

	/**
	 * Saves number of visits to file.
	 */
	@Override
	public void run()
	{
		// Int length.
		final int int_length = 4,
				  visits = Counter.getPageVisitsAndReset();
		
		// If no visits are to be saved...
		if(visits <= 0)
			// just return without saving anything.
			return;
		
		RandomAccessFile file = null;
		FileChannel channel = null;
		FileLock lock = null;
		
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
				// Acquire lock.
				lock = channel.lock();
				
				retry = false;
				
				// Allocate byte buffer.
				ByteBuffer out_buffer = ByteBuffer.allocate(int_length).putInt(visits);
				// Flip buffer for writing.
				out_buffer.flip();
				// Write out int.
				channel.write(out_buffer, 0);
				// Force write to file.
				channel.force(false);
				
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
				
			}
		}
	}
}
