package tech.tresearchgroup.babygalago.view.components;

import j2html.tags.DomContent;
import org.jetbrains.annotations.NotNull;

import static j2html.TagCreator.*;

public class HeadComponent {
    /**
     * Renders the head component
     *
     * @param title the title of the page
     * @return the component
     */
    public static @NotNull DomContent render(String title) {
        return head(
            meta().withCharset("UTF-8"),
            link().withHref("/assets/spectre.min.css").withRel("stylesheet"),
            link().withHref("/assets/gen/styles.min.css").withRel("stylesheet"),

            meta().withCharset("UTF-8").withContent("width=device-width, initial-scale=1.0").withName("viewport"),
            meta().withContent(title).withName("description"),
            title(title),
            link().withRel("icon").withType("image/x-icon").withHref("/assets/favicon.ico")
        );
    }
}
