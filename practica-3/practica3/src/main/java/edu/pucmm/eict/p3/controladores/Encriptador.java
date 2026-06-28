package edu.pucmm.eict.p3.controladores;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encriptador {

    private final StandardPBEStringEncryptor encryptor;

    public Encriptador() {
        encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("0417");
    }

    public String encriptar(String texto) {
        return encryptor.encrypt(texto);
    }

    public String desencriptar(String texto) {
        return encryptor.decrypt(texto);
    }
}

