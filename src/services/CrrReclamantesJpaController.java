/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Aseguradoras;
import models.CrrReclamantes;
import models.CrrReclamantesPK;
import models.CrrReclamo;
import services.exceptions.NonexistentEntityException;
import services.exceptions.PreexistingEntityException;

/**
 *
 * @author Dell
 */
public class CrrReclamantesJpaController implements Serializable {

    public CrrReclamantesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CrrReclamantes crrReclamantes) throws PreexistingEntityException, Exception {
        if (crrReclamantes.getCrrReclamantesPK() == null) {
            crrReclamantes.setCrrReclamantesPK(new CrrReclamantesPK());
        }
        crrReclamantes.getCrrReclamantesPK().setNumeroreclamo(crrReclamantes.getCrrReclamo().getNumeroreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aseguradoras codaseguradora = crrReclamantes.getCodaseguradora();
            if (codaseguradora != null) {
                codaseguradora = em.getReference(codaseguradora.getClass(), codaseguradora.getAseguradoraid());
                crrReclamantes.setCodaseguradora(codaseguradora);
            }
            CrrReclamo crrReclamo = crrReclamantes.getCrrReclamo();
            if (crrReclamo != null) {
                crrReclamo = em.getReference(crrReclamo.getClass(), crrReclamo.getNumeroreclamo());
                crrReclamantes.setCrrReclamo(crrReclamo);
            }
            em.persist(crrReclamantes);
            if (codaseguradora != null) {
                codaseguradora.getCrrReclamantesList().add(crrReclamantes);
                codaseguradora = em.merge(codaseguradora);
            }
            if (crrReclamo != null) {
                crrReclamo.getCrrReclamantesList().add(crrReclamantes);
                crrReclamo = em.merge(crrReclamo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCrrReclamantes(crrReclamantes.getCrrReclamantesPK()) != null) {
                throw new PreexistingEntityException("CrrReclamantes " + crrReclamantes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CrrReclamantes crrReclamantes) throws NonexistentEntityException, Exception {
        crrReclamantes.getCrrReclamantesPK().setNumeroreclamo(crrReclamantes.getCrrReclamo().getNumeroreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CrrReclamantes persistentCrrReclamantes = em.find(CrrReclamantes.class, crrReclamantes.getCrrReclamantesPK());
            Aseguradoras codaseguradoraOld = persistentCrrReclamantes.getCodaseguradora();
            Aseguradoras codaseguradoraNew = crrReclamantes.getCodaseguradora();
            CrrReclamo crrReclamoOld = persistentCrrReclamantes.getCrrReclamo();
            CrrReclamo crrReclamoNew = crrReclamantes.getCrrReclamo();
            if (codaseguradoraNew != null) {
                codaseguradoraNew = em.getReference(codaseguradoraNew.getClass(), codaseguradoraNew.getAseguradoraid());
                crrReclamantes.setCodaseguradora(codaseguradoraNew);
            }
            if (crrReclamoNew != null) {
                crrReclamoNew = em.getReference(crrReclamoNew.getClass(), crrReclamoNew.getNumeroreclamo());
                crrReclamantes.setCrrReclamo(crrReclamoNew);
            }
            crrReclamantes = em.merge(crrReclamantes);
            if (codaseguradoraOld != null && !codaseguradoraOld.equals(codaseguradoraNew)) {
                codaseguradoraOld.getCrrReclamantesList().remove(crrReclamantes);
                codaseguradoraOld = em.merge(codaseguradoraOld);
            }
            if (codaseguradoraNew != null && !codaseguradoraNew.equals(codaseguradoraOld)) {
                codaseguradoraNew.getCrrReclamantesList().add(crrReclamantes);
                codaseguradoraNew = em.merge(codaseguradoraNew);
            }
            if (crrReclamoOld != null && !crrReclamoOld.equals(crrReclamoNew)) {
                crrReclamoOld.getCrrReclamantesList().remove(crrReclamantes);
                crrReclamoOld = em.merge(crrReclamoOld);
            }
            if (crrReclamoNew != null && !crrReclamoNew.equals(crrReclamoOld)) {
                crrReclamoNew.getCrrReclamantesList().add(crrReclamantes);
                crrReclamoNew = em.merge(crrReclamoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CrrReclamantesPK id = crrReclamantes.getCrrReclamantesPK();
                if (findCrrReclamantes(id) == null) {
                    throw new NonexistentEntityException("The crrReclamantes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrrReclamantesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CrrReclamantes crrReclamantes;
            try {
                crrReclamantes = em.getReference(CrrReclamantes.class, id);
                crrReclamantes.getCrrReclamantesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crrReclamantes with id " + id + " no longer exists.", enfe);
            }
            Aseguradoras codaseguradora = crrReclamantes.getCodaseguradora();
            if (codaseguradora != null) {
                codaseguradora.getCrrReclamantesList().remove(crrReclamantes);
                codaseguradora = em.merge(codaseguradora);
            }
            CrrReclamo crrReclamo = crrReclamantes.getCrrReclamo();
            if (crrReclamo != null) {
                crrReclamo.getCrrReclamantesList().remove(crrReclamantes);
                crrReclamo = em.merge(crrReclamo);
            }
            em.remove(crrReclamantes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CrrReclamantes> findCrrReclamantesEntities() {
        return findCrrReclamantesEntities(true, -1, -1);
    }

    public List<CrrReclamantes> findCrrReclamantesEntities(int maxResults, int firstResult) {
        return findCrrReclamantesEntities(false, maxResults, firstResult);
    }

    private List<CrrReclamantes> findCrrReclamantesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CrrReclamantes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CrrReclamantes findCrrReclamantes(CrrReclamantesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CrrReclamantes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrrReclamantesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CrrReclamantes> rt = cq.from(CrrReclamantes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
