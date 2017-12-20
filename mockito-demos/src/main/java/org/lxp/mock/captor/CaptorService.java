package org.lxp.mock.captor;

import java.util.List;

public interface CaptorService {
    public void execute(List<CaptorModel> models);

    public String execute(CaptorModel model);
}
