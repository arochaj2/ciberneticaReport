/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import models.Aseguradoras;
import models.CrrReclamantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.CrrReclamo;
import services.exceptions.NonexistentEntityException;
import services.exceptions.PreexistingEntityException;

/**
 *
 * @author Dell
 */
public class CrrReclamoJpaController implements Serializable {

    public CrrReclamoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CrrReclamo crrReclamo) throws PreexistingEntityException, Exception {
        if (crrReclamo.getCrrReclamantesList() == null) {
            crrReclamo.setCrrReclamantesList(new ArrayList<CrrReclamantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aseguradoras codaseguradora = crrReclamo.getCodaseguradora();
            if (codaseguradora != null) {
                codaseguradora = em.getReference(codaseguradora.getClass(), codaseguradora.getAseguradoraid());
                crrReclamo.setCodaseguradora(codaseguradora);
            }
            List<CrrReclamantes> attachedCrrReclamantesList = new ArrayList<CrrReclamantes>();
            for (CrrReclamantes crrReclamantesListCrrReclamantesToAttach : crrReclamo.getCrrReclamantesList()) {
                crrReclamantesListCrrReclamantesToAttach = em.getReference(crrReclamantesListCrrReclamantesToAttach.getClass(), crrReclamantesListCrrReclamantesToAttach.getCrrReclamantesPK());
                attachedCrrReclamantesList.add(crrReclamantesListCrrReclamantesToAttach);
            }
            crrReclamo.setCrrReclamantesList(attachedCrrReclamantesList);
            em.persist(crrReclamo);
            if (codaseguradora != null) {
                codaseguradora.getCrrReclamoList().add(crrReclamo);
                codaseguradora = em.merge(codaseguradora);
            }
            for (CrrReclamantes crrReclamantesListCrrReclamantes : crrReclamo.getCrrReclamantesList()) {
                CrrReclamo oldCrrReclamoOfCrrReclamantesListCrrReclamantes = crrReclamantesListCrrReclamantes.getCrrReclamo();
                crrReclamantesListCrrReclamantes.setCrrReclamo(crrReclamo);
                crrReclamantesListCrrReclamantes = em.merge(crrReclamantesListCrrReclamantes);
                if (oldCrrReclamoOfCrrReclamantesListCrrReclamantes != null) {
                    oldCrrReclamoOfCrrReclamantesListCrrReclamantes.getCrrReclamantesList().remove(crrReclamantesListCrrReclamantes);
                    oldCrrReclamoOfCrrReclamantesListCrrReclamantes = em.merge(oldCrrReclamoOfCrrReclamantesListCrrReclamantes);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCrrReclamo(crrReclamo.getNumeroreclamo()) != null) {
                throw new PreexistingEntityException("CrrReclamo " + crrReclamo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CrrReclamo crrReclamo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CrrReclamo persistentCrrReclamo = em.find(CrrReclamo.class, crrReclamo.getNumeroreclamo());
            Aseguradoras codaseguradoraOld = persistentCrrReclamo.getCodaseguradora();
            Aseguradoras codaseguradoraNew = crrReclamo.getCodaseguradora();
            List<CrrReclamantes> crrReclamantesListOld = persistentCrrReclamo.getCrrReclamantesList();
            List<CrrReclamantes> crrReclamantesListNew = crrReclamo.getCrrReclamantesList();
            if (codaseguradoraNew != null) {
                codaseguradoraNew = em.getReference(codaseguradoraNew.getClass(), codaseguradoraNew.getAseguradoraid());
                crrReclamo.setCodaseguradora(codaseguradoraNew);
            }
            List<CrrReclamantes> attachedCrrReclamantesListNew = new ArrayList<CrrReclamantes>();
            for (CrrReclamantes crrReclamantesListNewCrrReclamantesToAttach : crrReclamantesListNew) {
                crrReclamantesListNewCrrReclamantesToAttach = em.getReference(crrReclamantesListNewCrrReclamantesToAttach.getClass(), crrReclamantesListNewCrrReclamantesToAttach.getCrrReclamantesPK());
                attachedCrrReclamantesListNew.add(crrReclamantesListNewCrrReclamantesToAttach);
            }
            crrReclamantesListNew = attachedCrrReclamantesListNew;
            crrReclamo.setCrrReclamantesList(crrReclamantesListNew);
            crrReclamo = em.merge(crrReclamo);
            if (codaseguradoraOld != null && !codaseguradoraOld.equals(codaseguradoraNew)) {
                codaseguradoraOld.getCrrReclamoList().remove(crrReclamo);
                codaseguradoraOld = em.merge(codaseguradoraOld);
            }
            if (codaseguradoraNew != null && !codaseguradoraNew.equals(codaseguradoraOld)) {
                codaseguradoraNew.getCrrReclamoList().add(crrReclamo);
                codaseguradoraNew = em.merge(codaseguradoraNew);
            }
            for (CrrReclamantes crrReclamantesListOldCrrReclamantes : crrReclamantesListOld) {
                if (!crrReclamantesListNew.contains(crrReclamantesListOldCrrReclamantes)) {
                    crrReclamantesListOldCrrReclamantes.setCrrReclamo(null);
                    crrReclamantesListOldCrrReclamantes = em.merge(crrReclamantesListOldCrrReclamantes);
                }
            }
            for (CrrReclamantes crrReclamantesListNewCrrReclamantes : crrReclamantesListNew) {
                if (!crrReclamantesListOld.contains(crrReclamantesListNewCrrReclamantes)) {
                    CrrReclamo oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes = crrReclamantesListNewCrrReclamantes.getCrrReclamo();
                    crrReclamantesListNewCrrReclamantes.setCrrReclamo(crrReclamo);
                    crrReclamantesListNewCrrReclamantes = em.merge(crrReclamantesListNewCrrReclamantes);
                    if (oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes != null && !oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes.equals(crrReclamo)) {
                        oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes.getCrrReclamantesList().remove(crrReclamantesListNewCrrReclamantes);
                        oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes = em.merge(oldCrrReclamoOfCrrReclamantesListNewCrrReclamantes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = crrReclamo.getNumeroreclamo();
                if (findCrrReclamo(id) == null) {
                    throw new NonexistentEntityException("The crrReclamo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CrrReclamo crrReclamo;
            try {
                crrReclamo = em.getReference(CrrReclamo.class, id);
                crrReclamo.getNumeroreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crrReclamo with id " + id + " no longer exists.", enfe);
            }
            Aseguradoras codaseguradora = crrReclamo.getCodaseguradora();
            if (codaseguradora != null) {
                codaseguradora.getCrrReclamoList().remove(crrReclamo);
                codaseguradora = em.merge(codaseguradora);
            }
            List<CrrReclamantes> crrReclamantesList = crrReclamo.getCrrReclamantesList();
            for (CrrReclamantes crrReclamantesListCrrReclamantes : crrReclamantesList) {
                crrReclamantesListCrrReclamantes.setCrrReclamo(null);
                crrReclamantesListCrrReclamantes = em.merge(crrReclamantesListCrrReclamantes);
            }
            em.remove(crrReclamo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CrrReclamo> findCrrReclamoEntities() {
        return findCrrReclamoEntities(true, -1, -1);
    }

    public List<CrrReclamo> findCrrReclamoEntities(int maxResults, int firstResult) {
        return findCrrReclamoEntities(false, maxResults, firstResult);
    }

    private List<CrrReclamo> findCrrReclamoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CrrReclamo.class));
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

    public CrrReclamo findCrrReclamo(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CrrReclamo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrrReclamoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CrrReclamo> rt = cq.from(CrrReclamo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
