package com.libraryapp.test.functional;

import static com.libraryapp.test.utils.TestUtils.businessTestFile;
import static com.libraryapp.test.utils.TestUtils.currentTest;
import static com.libraryapp.test.utils.TestUtils.testReport;
import static com.libraryapp.test.utils.TestUtils.yakshaAssert;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import mainapp.MyApp;

public class MainFunctionalTest {

    @AfterAll
    public static void afterAll() {
        testReport();
    }

    @Test
    public void testDirectoryExists() throws IOException {
        try {
            String output = MyApp.directoryExists();
            System.out.println(output);
            yakshaAssert(currentTest(), output.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    @Test
    public void testAtleastOneCommitToRevert() throws IOException {
        try {
            String output = MyApp.atleastOneCommitToRevert();
            System.out.println(output);
            yakshaAssert(currentTest(), output.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }

    @Test
    public void testRevertSuccess() throws IOException {
        try {
            String output = MyApp.revertSuccess();
            System.out.println(output);
            yakshaAssert(currentTest(), output.equals("true"), businessTestFile);
        } catch (Exception ex) {
            yakshaAssert(currentTest(), false, businessTestFile);
        }
    }
}
