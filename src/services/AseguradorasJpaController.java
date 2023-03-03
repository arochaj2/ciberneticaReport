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
import models.CrrReclamo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import models.Aseguradoras;
import models.CrrReclamantes;
import services.exceptions.NonexistentEntityException;

/**
 *
 * @author Dell
 */
public class AseguradorasJpaController implements Serializable {

    public AseguradorasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aseguradoras aseguradoras) {
        if (aseguradoras.getCrrReclamoList() == null) {
            aseguradoras.setCrrReclamoList(new ArrayList<CrrReclamo>());
        }
        if (aseguradoras.getCrrReclamantesList() == null) {
            aseguradoras.setCrrReclamantesList(new ArrayList<CrrReclamantes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<CrrReclamo> attachedCrrReclamoList = new ArrayList<CrrReclamo>();
            for (CrrReclamo crrReclamoListCrrReclamoToAttach : aseguradoras.getCrrReclamoList()) {
                crrReclamoListCrrReclamoToAttach = em.getReference(crrReclamoListCrrReclamoToAttach.getClass(), crrReclamoListCrrReclamoToAttach.getNumeroreclamo());
                attachedCrrReclamoList.add(crrReclamoListCrrReclamoToAttach);
            }
            aseguradoras.setCrrReclamoList(attachedCrrReclamoList);
            List<CrrReclamantes> attachedCrrReclamantesList = new ArrayList<CrrReclamantes>();
            for (CrrReclamantes crrReclamantesListCrrReclamantesToAttach : aseguradoras.getCrrReclamantesList()) {
                crrReclamantesListCrrReclamantesToAttach = em.getReference(crrReclamantesListCrrReclamantesToAttach.getClass(), crrReclamantesListCrrReclamantesToAttach.getCrrReclamantesPK());
                attachedCrrReclamantesList.add(crrReclamantesListCrrReclamantesToAttach);
            }
            aseguradoras.setCrrReclamantesList(attachedCrrReclamantesList);
            em.persist(aseguradoras);
            for (CrrReclamo crrReclamoListCrrReclamo : aseguradoras.getCrrReclamoList()) {
                Aseguradoras oldCodaseguradoraOfCrrReclamoListCrrReclamo = crrReclamoListCrrReclamo.getCodaseguradora();
                crrReclamoListCrrReclamo.setCodaseguradora(aseguradoras);
                crrReclamoListCrrReclamo = em.merge(crrReclamoListCrrReclamo);
                if (oldCodaseguradoraOfCrrReclamoListCrrReclamo != null) {
                    oldCodaseguradoraOfCrrReclamoListCrrReclamo.getCrrReclamoList().remove(crrReclamoListCrrReclamo);
                    oldCodaseguradoraOfCrrReclamoListCrrReclamo = em.merge(oldCodaseguradoraOfCrrReclamoListCrrReclamo);
                }
            }
            for (CrrReclamantes crrReclamantesListCrrReclamantes : aseguradoras.getCrrReclamantesList()) {
                Aseguradoras oldCodaseguradoraOfCrrReclamantesListCrrReclamantes = crrReclamantesListCrrReclamantes.getCodaseguradora();
                crrReclamantesListCrrReclamantes.setCodaseguradora(aseguradoras);
                crrReclamantesListCrrReclamantes = em.merge(crrReclamantesListCrrReclamantes);
                if (oldCodaseguradoraOfCrrReclamantesListCrrReclamantes != null) {
                    oldCodaseguradoraOfCrrReclamantesListCrrReclamantes.getCrrReclamantesList().remove(crrReclamantesListCrrReclamantes);
                    oldCodaseguradoraOfCrrReclamantesListCrrReclamantes = em.merge(oldCodaseguradoraOfCrrReclamantesListCrrReclamantes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aseguradoras aseguradoras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aseguradoras persistentAseguradoras = em.find(Aseguradoras.class, aseguradoras.getAseguradoraid());
            List<CrrReclamo> crrReclamoListOld = persistentAseguradoras.getCrrReclamoList();
            List<CrrReclamo> crrReclamoListNew = aseguradoras.getCrrReclamoList();
            List<CrrReclamantes> crrReclamantesListOld = persistentAseguradoras.getCrrReclamantesList();
            List<CrrReclamantes> crrReclamantesListNew = aseguradoras.getCrrReclamantesList();
            List<CrrReclamo> attachedCrrReclamoListNew = new ArrayList<CrrReclamo>();
            for (CrrReclamo crrReclamoListNewCrrReclamoToAttach : crrReclamoListNew) {
                crrReclamoListNewCrrReclamoToAttach = em.getReference(crrReclamoListNewCrrReclamoToAttach.getClass(), crrReclamoListNewCrrReclamoToAttach.getNumeroreclamo());
                attachedCrrReclamoListNew.add(crrReclamoListNewCrrReclamoToAttach);
            }
            crrReclamoListNew = attachedCrrReclamoListNew;
            aseguradoras.setCrrReclamoList(crrReclamoListNew);
            List<CrrReclamantes> attachedCrrReclamantesListNew = new ArrayList<CrrReclamantes>();
            for (CrrReclamantes crrReclamantesListNewCrrReclamantesToAttach : crrReclamantesListNew) {
                crrReclamantesListNewCrrReclamantesToAttach = em.getReference(crrReclamantesListNewCrrReclamantesToAttach.getClass(), crrReclamantesListNewCrrReclamantesToAttach.getCrrReclamantesPK());
                attachedCrrReclamantesListNew.add(crrReclamantesListNewCrrReclamantesToAttach);
            }
            crrReclamantesListNew = attachedCrrReclamantesListNew;
            aseguradoras.setCrrReclamantesList(crrReclamantesListNew);
            aseguradoras = em.merge(aseguradoras);
            for (CrrReclamo crrReclamoListOldCrrReclamo : crrReclamoListOld) {
                if (!crrReclamoListNew.contains(crrReclamoListOldCrrReclamo)) {
                    crrReclamoListOldCrrReclamo.setCodaseguradora(null);
                    crrReclamoListOldCrrReclamo = em.merge(crrReclamoListOldCrrReclamo);
                }
            }
            for (CrrReclamo crrReclamoListNewCrrReclamo : crrReclamoListNew) {
                if (!crrReclamoListOld.contains(crrReclamoListNewCrrReclamo)) {
                    Aseguradoras oldCodaseguradoraOfCrrReclamoListNewCrrReclamo = crrReclamoListNewCrrReclamo.getCodaseguradora();
                    crrReclamoListNewCrrReclamo.setCodaseguradora(aseguradoras);
                    crrReclamoListNewCrrReclamo = em.merge(crrReclamoListNewCrrReclamo);
                    if (oldCodaseguradoraOfCrrReclamoListNewCrrReclamo != null && !oldCodaseguradoraOfCrrReclamoListNewCrrReclamo.equals(aseguradoras)) {
                        oldCodaseguradoraOfCrrReclamoListNewCrrReclamo.getCrrReclamoList().remove(crrReclamoListNewCrrReclamo);
                        oldCodaseguradoraOfCrrReclamoListNewCrrReclamo = em.merge(oldCodaseguradoraOfCrrReclamoListNewCrrReclamo);
                    }
                }
            }
            for (CrrReclamantes crrReclamantesListOldCrrReclamantes : crrReclamantesListOld) {
                if (!crrReclamantesListNew.contains(crrReclamantesListOldCrrReclamantes)) {
                    crrReclamantesListOldCrrReclamantes.setCodaseguradora(null);
                    crrReclamantesListOldCrrReclamantes = em.merge(crrReclamantesListOldCrrReclamantes);
                }
            }
            for (CrrReclamantes crrReclamantesListNewCrrReclamantes : crrReclamantesListNew) {
                if (!crrReclamantesListOld.contains(crrReclamantesListNewCrrReclamantes)) {
                    Aseguradoras oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes = crrReclamantesListNewCrrReclamantes.getCodaseguradora();
                    crrReclamantesListNewCrrReclamantes.setCodaseguradora(aseguradoras);
                    crrReclamantesListNewCrrReclamantes = em.merge(crrReclamantesListNewCrrReclamantes);
                    if (oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes != null && !oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes.equals(aseguradoras)) {
                        oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes.getCrrReclamantesList().remove(crrReclamantesListNewCrrReclamantes);
                        oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes = em.merge(oldCodaseguradoraOfCrrReclamantesListNewCrrReclamantes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aseguradoras.getAseguradoraid();
                if (findAseguradoras(id) == null) {
                    throw new NonexistentEntityException("The aseguradoras with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aseguradoras aseguradoras;
            try {
                aseguradoras = em.getReference(Aseguradoras.class, id);
                aseguradoras.getAseguradoraid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aseguradoras with id " + id + " no longer exists.", enfe);
            }
            List<CrrReclamo> crrReclamoList = aseguradoras.getCrrReclamoList();
            for (CrrReclamo crrReclamoListCrrReclamo : crrReclamoList) {
                crrReclamoListCrrReclamo.setCodaseguradora(null);
                crrReclamoListCrrReclamo = em.merge(crrReclamoListCrrReclamo);
            }
            List<CrrReclamantes> crrReclamantesList = aseguradoras.getCrrReclamantesList();
            for (CrrReclamantes crrReclamantesListCrrReclamantes : crrReclamantesList) {
                crrReclamantesListCrrReclamantes.setCodaseguradora(null);
                crrReclamantesListCrrReclamantes = em.merge(crrReclamantesListCrrReclamantes);
            }
            em.remove(aseguradoras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aseguradoras> findAseguradorasEntities() {
        return findAseguradorasEntities(true, -1, -1);
    }

    public List<Aseguradoras> findAseguradorasEntities(int maxResults, int firstResult) {
        return findAseguradorasEntities(false, maxResults, firstResult);
    }

    private List<Aseguradoras> findAseguradorasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aseguradoras.class));
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

    public Aseguradoras findAseguradoras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aseguradoras.class, id);
        } finally {
            em.close();
        }
    }

    public int getAseguradorasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aseguradoras> rt = cq.from(Aseguradoras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
