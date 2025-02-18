package cifrado;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class GestorCifrado {
    KeyPair claves;
    KeyPairGenerator generadorClaves;
    Cipher cifrador;
    public GestorCifrado()
                    throws NoSuchAlgorithmException,
                    NoSuchPaddingException{
            generadorClaves=
                            KeyPairGenerator.getInstance("RSA");
            /*Usaremos una longitud de clave
             * de 1024 bits */
            generadorClaves.initialize(1024);
            claves=generadorClaves.generateKeyPair();
            cifrador=Cipher.getInstance("RSA");
    }
    public PublicKey getPublica(){
            return claves.getPublic();
    }
    public PrivateKey getPrivada(){
            return claves.getPrivate();
    }

    public byte[] cifrar(byte[] paraCifrar,
                    Key claveCifrado
                    ) throws InvalidKeyException,
                    IllegalBlockSizeException,
                    BadPaddingException{
            byte[] resultado;
            /* Se pone el cifrador en modo cifrado*/
            cifrador.init(Cipher.ENCRYPT_MODE,
                            claveCifrado);
            resultado=cifrador.doFinal(paraCifrar);
            return resultado;
    }

    public byte[] descifrar(
                    byte[] paraDescifrar,
                    Key claveDescifrado)
                                    throws InvalidKeyException,
                                    IllegalBlockSizeException,
                                    BadPaddingException{
            byte[] resultado;
            /* Se pone el cifrador en modo descifrado*/
            cifrador.init(Cipher.DECRYPT_MODE,
                            claveDescifrado);
            resultado=cifrador.doFinal(paraDescifrar);
            return resultado;
    }



    public static void main(String[] args)
                    throws NoSuchAlgorithmException,
                    NoSuchPaddingException,
                    InvalidKeyException,
                    IllegalBlockSizeException,
                    BadPaddingException,
                    UnsupportedEncodingException
    {
            GestorCifrado gestorCifrado=
                            new GestorCifrado();
            String mensajeOriginal="Hola mundo";
            Key clavePublica=gestorCifrado.getPublica();

            byte[] mensajeCifrado=
                            gestorCifrado.cifrar(
                                            mensajeOriginal.getBytes(),
                                            clavePublica
            );
            String cadCifrada=
                            new String(mensajeCifrado, "UTF-8");

            System.out.println
                    ("Cadena original:"+mensajeOriginal);
            System.out.println
                    ("Cadena cifrada:"+cadCifrada);

            /* Cogemos la cadCifrada y la desciframos
             * con la otra clave */
            Key clavePrivada;
            clavePrivada=gestorCifrado.getPrivada();
            byte[] descifrada=
                            gestorCifrado.descifrar(
                                            mensajeCifrado,clavePrivada);
            String mensajeDescifrado;
            /* E imprimimos el mensaje*/
            mensajeDescifrado=
                            new String(descifrada, "UTF-8");
            System.out.println(
                            "El mensaje descifrado es:"+
                                            mensajeDescifrado);
    }
}