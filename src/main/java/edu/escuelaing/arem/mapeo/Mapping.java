
package edu.escuelaing.arem.mapeo;
/**
 *anotacion Mapping para los metodos
 * @author Alejnadro Rodriguez del Toro
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Mapping {
    String value();
}