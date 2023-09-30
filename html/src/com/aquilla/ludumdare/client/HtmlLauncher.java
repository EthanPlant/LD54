package com.aquilla.ludumdare.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.aquilla.ludumdare.LudumDare;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(LudumDare.WIDTH * LudumDare.SCALE,
                        LudumDare.HEIGHT * LudumDare.SCALE);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new LudumDare();
        }
}