package jota.server.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jota.server.dao.BaseDao;
import jota.server.dto.FiltroServer;
import jota.server.dto.FiltroWhere;
import jota.server.entity.Auditable;
import jota.server.entity.Compartible;
import jota.server.entity.EsAuditable;
import jota.server.entity.EsCompartible;
import jota.server.entity.ICheckPermisos;
import jota.server.entity.UsuarioDeCompartible;
import jota.server.enumerations.RolEnum;
import jota.server.exceptions.DaoException;
import jota.server.exceptions.PermisoAccesoException;
import jota.server.exceptions.ServiceException;
import jota.server.utils.SecurityUtils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public abstract class BaseDaoImpl<E> implements BaseDao<E> {

	private Class<E> entityClass;

	protected BaseDaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Autowired
	private SessionFactory sessionFactory;

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public E getById( Serializable id ) throws DaoException {
		try {
			E e = (E) getCurrentSession().get( entityClass, id );
			if ( e instanceof ICheckPermisos ) {
				((ICheckPermisos) e).checkPermisoGet();
			}
			return e;
		} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}
	/*
    @Override
    public void saveOrUpdate( E e ) throws DaoException {
    	try {
    		getCurrentSession().saveOrUpdate(e);
    	} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
    }
	 */
	@Override
	public void delete( E e ) throws DaoException {
		Assert.notNull( e, "La entidad no puede ser null" );
		try {
			if ( e instanceof ICheckPermisos ) {
				((ICheckPermisos) e).checkPermisoDelete();
			}
			getCurrentSession().delete(e);
		}catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}
	/*
	@Override
	public List<E> getAll() throws DaoException {
		List<E> dev = new ArrayList<E>();
		try {
			Criteria criteria = getCurrentSession().createCriteria(entityClass);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			dev  = criteria.list();
		} catch (Exception ex) {
			throw new DaoException( ex );
		}
		return dev;
	}
	 */
	@Override
	public void merge( E e ) throws DaoException {
		Assert.notNull( e, "La entidad no puede ser null" );
		try {
			if ( e instanceof ICheckPermisos ) {
				((ICheckPermisos) e).checkPermisoUpdate();
			}
			if ( e instanceof EsAuditable ){
				((EsAuditable) e).getAuditable().auditUpdate();
			}
			getCurrentSession().merge(e);
		} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}

	@Override
	public void save( E e ) throws DaoException {
		Assert.notNull( e, "La entidad no puede ser null" );
		try {
			if ( e instanceof ICheckPermisos ) {
				((ICheckPermisos) e).checkPermisoSave();
			}
			if ( e instanceof EsAuditable ){
				((EsAuditable) e).getAuditable().auditInsert();
			}
			getCurrentSession().save(e);
		} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}

	@Override
	public void update( E e ) throws DaoException {
		Assert.notNull( e, "La entidad no puede ser null" );
		try {
			if ( e instanceof ICheckPermisos ) {
				((ICheckPermisos) e).checkPermisoUpdate();
			}
			if ( e instanceof EsAuditable ){
				((EsAuditable) e).getAuditable().auditUpdate();
			}
//			checkPermisoEscritura( e );
//			auditUpdate( e );
			getCurrentSession().update(e);
		} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}

	public void evict( E e ) throws DaoException {
		try {
			getCurrentSession().evict( e );
		} catch ( Exception ex ) {
			throw new DaoException( ex );
		}
	}

	@Override
	public List<E> findByFiltro( int start, int size, FiltroServer filtro ) throws DaoException {
		Assert.notNull( filtro, "El filtro no puede ser null" );
		List<E> dev = new ArrayList<E>();
		try {
			String query = "SELECT e FROM " + entityClass.getName() + " e";
			String where = " WHERE 1=1 ";
			if ( entityClass.newInstance() instanceof ICheckPermisos ) {
				((ICheckPermisos) entityClass.newInstance()).getFiltroFind();
			}

			if ( !SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
				if ( SecurityUtils.getIdUsuario() == null) {
					if ( entityClass.newInstance() instanceof EsCompartible ) {
						query += " left join e.compartible.usuarios u";
						filtro.add( new FiltroWhere( " or e.compartible.publico = :publico", "publico", true) );
					} 
				} else {
					if ( entityClass.newInstance() instanceof EsAuditable ) {
						filtro.add( new FiltroWhere( " and e.auditable.creadoPor = :idUsuario", "idUsuario", SecurityUtils.getIdUsuario() ) );
					}
					if ( entityClass.newInstance() instanceof EsCompartible ) {
						query += " left join e.compartible.usuarios u";
						filtro.add( new FiltroWhere( " or e.compartible.publico = :publico", "publico", true) );
						filtro.add( new FiltroWhere( " or u.pk.usuario.id = :idUsuario", "idUsuario", SecurityUtils.getIdUsuario() ) );
					} 
				}
			}
			for ( FiltroWhere filtroWhere : filtro.getWhere() ) {
				where += filtroWhere.getSql();
			}

			Query result = getCurrentSession().createQuery( query + where );
			for ( FiltroWhere filtroWhere : filtro.getWhere() ) {
				result.setParameter( filtroWhere.getParameter(), filtroWhere.getValor() );
			}
			result.setFirstResult( start );
			if ( size > 0 ) {
				result.setMaxResults( size );
			}

			dev = result.list();
		} catch (Exception e) {
			throw new DaoException( e );
		}
		return dev;
	}

	protected E checkPermisoBorrado( E e ) throws ServiceException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return e;
		}
		if ( !( e instanceof EsAuditable ) ) {
			return e;
		}
		// Es mia o es publica
		if ( esMia( e ) || esPublica( e ) ) {
			return e;
		}
		throw new PermisoAccesoException("No tienes permiso para borrar este objeto");
	}

	protected E checkPermisoLectura( E e ) throws ServiceException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return e;
		}
		if ( !( e instanceof EsAuditable ) ) {
			return e;
		}
		// Es mia o es publica o tengo permiso
		if ( esMia( e ) || esPublica( e ) || tengoPermisoLectura( e ) ) {
			return e;
		}
		throw new PermisoAccesoException("No tienes permiso para ver este objeto");
	}

	protected E checkPermisoEscritura( E e ) throws ServiceException {
		if ( SecurityUtils.hasRol( RolEnum.ADMINISTRADOR ) ) {
			return e;
		}
		if ( !( e instanceof EsAuditable ) ) {
			return e;
		}
		// Es mia o tengo permiso
		if ( esMia( e ) || tengoPermisoEscritura( e ) ) {
			return e;
		}
		throw new PermisoAccesoException("No tienes permiso para modificar este objeto");
	}

	private boolean esMia(E e) {
		if ( e instanceof EsAuditable ) {
			Auditable auditable = ((EsAuditable) e).getAuditable();
			if ( auditable.getCreadoPor().equals( SecurityUtils.getIdUsuario() ) ) {
				return true;
			}
		}
		return false;
	}

	private boolean esPublica(E e) {
		if ( e instanceof EsCompartible ) {
			Compartible compartible = ((EsCompartible) e).getCompartible();
			// Es publica
			if ( compartible.getPublico() ) {
				return true;
			}
		}
		return false;
	}

	private boolean tengoPermisoLectura(E e) {
		if ( e instanceof EsCompartible ) {
			Compartible compartible = ((EsCompartible) e).getCompartible();
			for ( UsuarioDeCompartible usuarioDeCompartible : compartible.getUsuarios() ) {
				if ( usuarioDeCompartible.getPk().getUsuario().getId().equals( SecurityUtils.getIdUsuario() ) ) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean tengoPermisoEscritura(E e) {
		//TODO esto de momento no se implementa
	/*	if ( e instanceof EsCompartible ) {
			Compartible compartible = ((EsCompartible) e).getCompartible();
			for ( UsuarioDeCompartible usuarioDeCompartible : compartible.getUsuarios() ) {
				if ( usuarioDeCompartible.getPk().getUsuario().getId().equals( SecurityUtils.getIdUsuario() ) && usuarioDeCompartible.getEscritura() ) {
					return true;
				}
			}
		}*/
		return false;
	}
}