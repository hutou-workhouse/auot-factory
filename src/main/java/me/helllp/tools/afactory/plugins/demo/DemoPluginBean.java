package me.helllp.tools.afactory.plugins.demo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.helllp.tools.afactory.core.BasePluginBean;

@Data
@EqualsAndHashCode(callSuper=true)
public class DemoPluginBean extends BasePluginBean{
	private List<ParamBean> params;
}
