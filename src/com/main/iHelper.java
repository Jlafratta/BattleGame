package com.main;

import java.util.Scanner;

/**
 *                 [ Interfaz del Helper ]
 *
 *  - > Su unica funcion es crear un Scanner y un Helper
 *  para que las clases que necesiten utilizarlos solo
 *  deban implementar iHelper, y no tengan que generar
 *  una instancia de estos cada vez que tengan que
 *  utilizar sus funciones.
 *
 ***/

public interface iHelper {

    Helper helper = new Helper();
    Scanner scan = new Scanner(System.in);

}
