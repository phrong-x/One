package com.kaiqi.love_family.view.dialog;

public enum Effectstype {
	 RotateBottom(RotateBottom.class);
	 private Class<? extends BaseEffects> effectsClazz;

	    private Effectstype(Class<? extends BaseEffects> mclass) {
	        effectsClazz = mclass;
	    }

	    public BaseEffects getAnimator() {
	        BaseEffects bEffects=null;
		try {
			bEffects = effectsClazz.newInstance();
		} catch (ClassCastException e) {
			throw new Error("Can not init animatorClazz instance");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			throw new Error("Can not init animatorClazz instance");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			throw new Error("Can not init animatorClazz instance");
		}
		return bEffects;
	    }
}
