package tech.tresearchgroup.colobus.view;

import org.jetbrains.annotations.NotNull;
import tech.tresearchgroup.babygalago.controller.controllers.QueueEntityController;
import tech.tresearchgroup.babygalago.view.components.HeadComponent;
import tech.tresearchgroup.babygalago.view.components.SideBarComponent;
import tech.tresearchgroup.babygalago.view.components.TopBarComponent;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;

import static j2html.TagCreator.*;

public class IndexPage {
    public byte @NotNull [] render(boolean loggedIn,
                                   long unreadCount,
                                   PermissionGroupEnum permissionGroupEnum,
                                   String serverName,
                                   boolean isEnableUpload,
                                   boolean isMovieLibraryEnable,
                                   boolean isTvShowLibraryEnable,
                                   boolean isGameLibraryEnable,
                                   boolean isMusicLibraryEnable,
                                   boolean isBookLibraryEnable) {
        return document(
            html(
                HeadComponent.render(serverName),
                //Todo load notifications
                TopBarComponent.render(unreadCount, QueueEntityController.getQueueSize(), true, permissionGroupEnum, isEnableUpload),
                SideBarComponent.render(
                    loggedIn,
                    isMovieLibraryEnable,
                    isTvShowLibraryEnable,
                    isGameLibraryEnable,
                    isMusicLibraryEnable,
                    isBookLibraryEnable
                ),
                body(
                    div(
                        div(
                            text("The forum is still being developed. Please check back later.")
                        ).withClass("verticalCenter subLabel")
                    ).withClass("body")
                )
            )
        ).getBytes();
    }
}
