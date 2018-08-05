/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spi.spi_print;

/**
 *
 * @author vasil
 */
public class spiPrint implements spi_lib.senderInterface{

    @Override
    public void send(String message) {
        System.out.println(String.format("message = %s", message));
    }
    
}
