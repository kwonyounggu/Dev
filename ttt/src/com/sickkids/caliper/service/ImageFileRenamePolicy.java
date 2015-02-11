package com.sickkids.caliper.service;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sickkids.caliper.common.Utils;

public class ImageFileRenamePolicy implements FileRenamePolicy
{

	//implement the rename(File f) method to satisfy the 
    // FileRenamePolicy interface contract
	public File rename(File f)
	{

		// Get the parent directory path as in h:/home/user or /home/user
		String parentDir = f.getParent();

		// Get filename without its path location, such as 'index.txt'
		String fname = f.getName();

		// Get the extension if the file has one
		String fileExt = "";
		int i = -1;
		if ((i = fname.indexOf(".")) != -1)
		{

			fileExt = fname.substring(i);
			fname = fname.substring(0, i);
		}

		// add the timestamp
		fname = fname + "_"+Utils.getDateTimeForFileName();
		// piece together the filename
		fname = parentDir + System.getProperty("file.separator") + fname + fileExt;

		File temp = new File(fname);

		return temp;
	}

}
