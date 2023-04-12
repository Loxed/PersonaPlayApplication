package com.example.personaplayfront.Repo;

import com.example.personaplayfront.Util.ConnectionUtil;
import com.example.personaplayfront.Model.Media;

import java.sql.PreparedStatement;
import java.util.List;

import org.hibernate.Transaction;

public class MediaDaoImpl extends DaoImpl<Media>{
    public MediaDaoImpl(Class<Media> type, ConnectionUtil conn) {
        super(type, conn);
    }

    @Override
    public void save(Media media) {
        Transaction transaction = null;
        try {
            String query = "INSERT INTO media(imdbID,Title,Poster,Genre,Year,Actors,Director,Plot) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(query);
            ps.setString(1, media.imdbId);
            ps.setString(2, media.mediaName);
            ps.setString(3, media.posterUrl);
            ps.setString(4, media.genres);
            ps.setString(5, media.year);
            ps.setString(6, media.actors);
            ps.setString(7, media.director);
            ps.setString(8, media.plot);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Media findById(int id) {
        try {
            String query = "SELECT * FROM media WHERE id = ?";
            PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeQuery();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Media media) {

    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public List<Media> findAll() {
        return null;
    }

    @Override
    public List<Media> findByProperty(String propertyName, Object propertyValue) {
        return null;
    }
}
