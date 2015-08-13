package jota.server.dao;

import java.io.Serializable;
import java.util.List;

import jota.server.dto.FiltroServer;
import jota.server.exceptions.DaoException;

public interface BaseDao<E> {

    E getById( Serializable id ) throws DaoException;
//	List<E> getAll() throws DaoException;
 //   void saveOrUpdate( E e ) throws DaoException;
    void merge( E e ) throws DaoException;
    void save( E e ) throws DaoException;
    void update( E e ) throws DaoException;
    void delete( E e ) throws DaoException;
    void evict( E e ) throws DaoException;
	List<E> findByFiltro( int start, int size, FiltroServer filtro ) throws DaoException;

}
