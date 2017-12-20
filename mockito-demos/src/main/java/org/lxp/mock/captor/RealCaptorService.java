package org.lxp.mock.captor;

public interface RealCaptorService {
    public void batchExecute(String propertyString, int propertyInt);

    public void execute(String propertyString, int propertyInt);

    public void execute(CaptorModel captorModel);
}
