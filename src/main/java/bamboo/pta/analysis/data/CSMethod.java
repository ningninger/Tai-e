package bamboo.pta.analysis.data;

import bamboo.pta.analysis.context.Context;
import bamboo.pta.element.Method;

public class CSMethod extends AbstractCSElement {

    private Method method;

    CSMethod(Context context, Method method) {
        super(context);
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return context + ":" + method;
    }
}