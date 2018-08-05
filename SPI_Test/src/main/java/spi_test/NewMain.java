/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spi_test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.ServiceLoader;
import spi_lib.senderInterface;

/**
 *
 * @author vasil
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here       
        
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Enumeration<URL> e = classLoader.getResources("");
//        while (e.hasMoreElements()) {
//            System.out.println("ClassLoader Resource: " + e.nextElement());
//        }
        
        System.out.println("test");
        String currentdir = System.getProperty("user.dir"); 
        System.out.println(currentdir + File.separator + "plugins");
        File loc = new File("c:/temp/plugins");

        File[] flist = loc.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return file.getPath().toLowerCase().endsWith(".jar");
            }
        });
        URL[] urls = new URL[flist.length];
        for (int i = 0; i < flist.length; i++) {
            urls[i] = flist[i].toURI().toURL();
            System.out.println("urls = " + urls[i].getPath());
        }
        URLClassLoader ucl = new URLClassLoader(urls);

        

        ServiceLoader<senderInterface> sloder = ServiceLoader.load(senderInterface.class, ucl);
       
        
        Iterator<senderInterface> apit = sloder.iterator();
        while (apit.hasNext())
            System.out.println(apit.next().getClass().getName());
        
        
        
        for (senderInterface hw : sloder) {
            hw.send("test " + hw.getClass().getName());
        }
    }

}
