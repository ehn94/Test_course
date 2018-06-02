///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package REST;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author Kaboka
// */
//public class APITest {
//    
//    public APITest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of getBookAuthorByCity method, of class API.
//     */
//    @Test
//    public void testGetBookAuthorByCity() throws Exception {
//        System.out.println("getBookAuthorByCity");
//        String city_name = "";
//        API instance = new API();
//        String expResult = "";
//        String result = instance.getBookAuthorByCity(city_name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCitiesByBookTitle method, of class API.
//     */
//    @Test
//    public void testGetCitiesByBookTitle() throws Exception {
//        System.out.println("getCitiesByBookTitle");
//        String book_title = "";
//        API instance = new API();
//        String expResult = "";
//        String result = instance.getCitiesByBookTitle(book_title);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBookAuthorCityByAuthor method, of class API.
//     */
//    @Test
//    public void testGetBookAuthorCityByAuthor() throws Exception {
//        System.out.println("getBookAuthorCityByAuthor");
//        String author_name = "";
//        API instance = new API();
//        String expResult = "";
//        String result = instance.getBookAuthorCityByAuthor(author_name);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getBookCityByGeolocation method, of class API.
//     */
//    @Test
//    public void testGetBookCityByGeolocation() throws Exception {
//        System.out.println("getBookCityByGeolocation");
//        String latitude = "";
//        String longitude = "";
//        API instance = new API();
//        String expResult = "";
//        String result = instance.getBookCityByGeolocation(latitude, longitude);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
