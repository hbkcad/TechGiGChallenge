package com.challenge.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.challenge.dao.IWordDao;
import com.challenge.model.Word;

@Service
public class WordServiceImpl implements IWordService{

	@Autowired
	IWordDao iWordDao;

	@Override
	public List<Word> getAllWords() {
		return iWordDao.findAll();
		
	}

	@Override
	public void uploadText(MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		getUniqueWordReadFromFile(file);
		
	}
	public  Set<String> getUniqueWordReadFromFile(MultipartFile file) throws IOException{
        Set<String> uniqueSet = null;
        StringBuffer stBuf = readFromFile(file);
        String dataReadFromFile[] = stBuf.toString().split(" ");
        uniqueSet = new TreeSet<String>();
        for(String data : dataReadFromFile) {
            uniqueSet.add(data);
            iWordDao.insertData(data);
        }
        
        return uniqueSet;
    }
    static StringBuffer readFromFile(MultipartFile file) throws IOException {
        BufferedReader bufferedReader = null;
        StringBuffer stBuf = new StringBuffer();
        File temp = File.createTempFile(file.getName(), ".txt");
        byte[] byteArr = file.getBytes();
        FileOutputStream fos = new FileOutputStream(temp);
        fos.write(byteArr);
        fos.flush();
        fos.close();
        System.out.println(temp.getAbsolutePath());
        try {
            bufferedReader= new BufferedReader(new FileReader(temp));
            String readData ="";
            while ((readData = bufferedReader.readLine()) != null) {
                stBuf.append(readData);
            }
            System.out.println("---?"+stBuf);
        } catch(IOException ioEx) {
            throw ioEx;
        } finally {
            if(null != bufferedReader)
                bufferedReader.close();
            	temp.delete();
        }
        return stBuf;
    }
}
