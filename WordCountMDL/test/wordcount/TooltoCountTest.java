package wordcount;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TooltoCountTest {

    @org.junit.Test
    public void testcountCharacters() {
        Set<String> filePaths = new HashSet<String>();
        String File1 = "E:\\test.java";
        String File2 = "E:\\WordCount";
        filePaths.add(File2);
        filePaths.add(File1);
        filePaths =new TooltoCount().getAllFilePaths(filePaths);
        String Result = new TooltoCount().countCharacters(filePaths);
        System.out.println(Result);
    }

    @org.junit.Test
    public void testcountWords() {
        Set<String> filePaths = new HashSet<String>();
        String File1 = "E:\\test.java";
        String File2 = "E:\\WordCount";
        String stopList = "";
        filePaths.add(File2);
        filePaths.add(File1);
        filePaths =new TooltoCount().getAllFilePaths(filePaths);
        System.out.println(new TooltoCount().countWords(filePaths));
    }

    @org.junit.Test
    public void testcountLines(){
        Set<String> filePaths = new HashSet<String>();
        String File1 = "E:\\test.java";
        String File2 = "E:\\WordCount";
        filePaths.add(File2);
        filePaths.add(File1);
        filePaths =new TooltoCount().getAllFilePaths(filePaths);
        System.out.println(new TooltoCount().countLines(filePaths));
    }

    @org.junit.Test
    public void testcountCodeLines(){
        Set<String> filePaths = new HashSet<String>();
        filePaths.add("E:\\out.c");
        String result = new TooltoCount().countCodeLines(filePaths);
        System.out.println(result);
    }

    @org.junit.Test
    public void testcountEmptyLines(){
        Set<String> filePaths = new HashSet<String>();
        filePaths.add("E:\\out.c");
        String result = new TooltoCount().countEmptyLines(filePaths);
        System.out.println(result);
    }

    @org.junit.Test
    public void countNoteLines(){
        Set<String> filePaths = new HashSet<String>();
        filePaths.add("E:\\out.c");
        String result = new TooltoCount().countNoteLines(filePaths);
        System.out.println(result);
    }
}