package raven.serializer.withJackson;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.raven.commons.data.annotation.Ignore;

/**
 * @author yi.liang
 * @date by 2018/1/8
 * @since JDK1.8
 */
public class CustomAnnotationIntrospector extends JacksonAnnotationIntrospector {

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember m) {

        return _isIgnorable(m) || super.hasIgnoreMarker(m);
    }

    protected boolean _isIgnorable(Annotated a) {
        return _findAnnotation(a, Ignore.class) != null;
    }
}
