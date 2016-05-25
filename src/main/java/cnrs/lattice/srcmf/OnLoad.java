package cnrs.lattice.srcmf;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;
 
@ManagedBean(name="onload")
@SessionScoped
public class OnLoad implements Serializable{
	private static final long serialVersionUID = 1L;
  /**
   * clean the update directory on page load
   * @throws Exception
   */
  public void onLoad() throws Exception{
  	FileUtils.cleanDirectory(new File(Params.userhome + Params.dir + "uploaded/"));
  }
}