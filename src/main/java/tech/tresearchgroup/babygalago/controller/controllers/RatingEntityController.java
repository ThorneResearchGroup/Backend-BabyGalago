package tech.tresearchgroup.babygalago.controller.controllers;

import com.google.gson.Gson;
import com.meilisearch.sdk.Client;
import com.zaxxer.hikari.HikariDataSource;
import io.activej.http.HttpResponse;
import io.activej.promise.Promisable;
import io.activej.serializer.BinarySerializer;
import tech.tresearchgroup.palila.controller.GenericController;
import tech.tresearchgroup.palila.model.Card;
import tech.tresearchgroup.palila.model.PageMediaEntity;
import tech.tresearchgroup.palila.model.enums.PermissionGroupEnum;
import tech.tresearchgroup.schemas.galago.entities.RatingEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RatingEntityController extends GenericController {
    private final HikariDataSource hikariDataSource;

    /**
     * Sets up the rating entity controller. To understand this class better, have a look at the class it extends (GenericController)
     */
    public RatingEntityController(HikariDataSource hikariDataSource,
                                  Gson gson,
                                  Client client,
                                  BinarySerializer<RatingEntity> serializer,
                                  int reindexSize,
                                  Object sample,
                                  ExtendedUserEntityController extendedUserEntityController) throws Exception {
        super(
            hikariDataSource,
            gson,
            client,
            RatingEntity.class,
            serializer,
            reindexSize,
            null,
            sample,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            PermissionGroupEnum.USER,
            extendedUserEntityController,
            new Card()
        );
        this.hikariDataSource = hikariDataSource;
    }

    /**
     * Gets the ratings for an entity
     *
     * @param mediaType the type of media
     * @param mediaId   the media id
     * @return the response page
     * @throws IOException  if it fails to parse the data
     * @throws SQLException if it fails to load the data from the db
     */
    public Promisable<HttpResponse> getRatings(String mediaType, int mediaId) throws IOException, SQLException {
        //Todo caching system and overrride the functions that don't include the media type
        Connection connection = hikariDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(" SELECT * FROM ratingentity WHERE mediaType = ? AND mediaId = ?");
        preparedStatement.setString(1, mediaType);
        preparedStatement.setInt(2, mediaId);
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getResultSet();
        connection.close();
        List artistRatings = genericDAO.getAllFromResultSet(resultSet, RatingEntity.class, false);
        if (artistRatings.size() > 0) {
            PageMediaEntity pageMediaEntity = new PageMediaEntity();
            pageMediaEntity.setEntities(artistRatings);
            return ok(gson.toJson(pageMediaEntity).getBytes());
        } else {
            return error();
        }
    }
}
