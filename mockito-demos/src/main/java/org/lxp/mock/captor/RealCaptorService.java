package org.lxp.mock.captor;

import java.util.List;

public interface RealCaptorService {
    public void batchExecute(String propertyString, int propertyInt);

    public void execute(String propertyString, int propertyInt);

    public void execute(CaptorModel captorModel);

    public List<String> execute(int index);
}
