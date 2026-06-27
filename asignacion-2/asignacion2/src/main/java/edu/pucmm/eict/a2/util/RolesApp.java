package edu.pucmm.eict.a2.util;

import io.javalin.security.RouteRole;

/**
 * Enum para manejar los roles de la aplicacion.
 */
public enum RolesApp implements RouteRole {
    CUALQUIERA,
    LOGUEADO,
    ROLE_USUARIO,
    ROLE_ADMIN;
}
