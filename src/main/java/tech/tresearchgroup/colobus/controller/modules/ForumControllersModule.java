package tech.tresearchgroup.colobus.controller.modules;

import io.activej.inject.annotation.Provides;
import io.activej.inject.module.AbstractModule;
import tech.tresearchgroup.babygalago.controller.SettingsController;
import tech.tresearchgroup.babygalago.controller.controllers.ExtendedUserEntityController;
import tech.tresearchgroup.babygalago.controller.controllers.NotificationEntityController;
import tech.tresearchgroup.colobus.controller.IndexController;
import tech.tresearchgroup.colobus.view.IndexPage;


public class ForumControllersModule extends AbstractModule {
    @Provides
    IndexController indexController(ExtendedUserEntityController extendedUserEntityController,
                                    IndexPage indexPage,
                                    NotificationEntityController notificationEntityController,
                                    SettingsController settingsController) {
        return new IndexController(
            extendedUserEntityController,
            indexPage,
            notificationEntityController,
            settingsController
        );
    }
}
