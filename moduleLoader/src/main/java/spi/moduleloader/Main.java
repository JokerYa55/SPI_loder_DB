/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spi.moduleloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.Session;

/**
 *
 * @author vasil
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("loder_JPA");
            EntityManager em = emf.createEntityManager();
            Session session = em.unwrap(Session.class);

            File file = new File("c:/temp/plugins/sayModule_1.jar");

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
                FileInputStream fis = new FileInputStream(urls[i].getPath());

                // Copy the data from the file to the large object
                byte buf[] = new byte[2048];
                int s, tl = 0;
                ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
                while ((s = fis.read(buf, 0, 2048)) > 0) {
                    //obj.write(buf, 0, s);
                    byteOutStream.write(buf, 0, s);
                    tl += s;
                }

                TModule module = new TModule();
                module.setFModule(byteOutStream.toByteArray());
                em.getTransaction().begin();
                em.persist(module);
                em.getTransaction().commit();
            }

            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
