package edu.escuelaing.arem.mapeo;

/**
 *Anotacion Component para las clases
 * @author Alejnadro Rodriguez del Toro
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {

}
