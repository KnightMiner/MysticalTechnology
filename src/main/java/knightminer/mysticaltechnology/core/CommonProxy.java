package knightminer.mysticaltechnology.core;

import knightminer.mysticaltechnology.MysticalTechnology;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

public class CommonProxy {

	public void preInit() {
		if(!Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
			MysticalTechnology.log.error(
					"Proxy.preInit has to be called during Pre-Initialisation.");
		}

		registerModels();
	}

	public void init() {
		if(!Loader.instance().isInState(LoaderState.INITIALIZATION)) {
			MysticalTechnology.log.error(
					"Proxy.init has to be called during Initialisation.");
		}
	}

	public void postInit() {
		if(!Loader.instance().isInState(LoaderState.POSTINITIALIZATION)) {
			MysticalTechnology.log.error(
					"Proxy.postInit has to be called during Post-Initialisation.");
		}
	}

	protected void registerModels() {
		if(Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
			MysticalTechnology.log.error(
					"Proxy.registerModels has to be called during preInit. Otherwise the models wont be found on first load.");
		}
	}
}
