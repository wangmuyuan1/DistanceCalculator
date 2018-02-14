package com.example.distancecalculator.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileService
{
    public List<String> getFile(String fileName)
    {
        File file = new File(fileName);

        try
        {
            return FileUtils.readLines(file, "UTF-8");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
