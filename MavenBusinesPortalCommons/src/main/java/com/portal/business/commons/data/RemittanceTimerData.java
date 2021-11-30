package com.portal.business.commons.data;


import com.portal.business.commons.generic.AbstractBusinessPortalWs;
import com.portal.business.commons.utils.EjbConstants;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.apache.log4j.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;

/**
 *
 * @author usuario
 */
public class RemittanceTimerData extends AbstractBusinessPortalWs {


  private static final Logger LOG = Logger.getLogger(RemittanceTimerData.class);

    @Resource
    private SessionContext ctx;
    Calendar initialExpiration;
    private Long timeoutInterval = 0L;


    private void cancelTimers() {
        try {
            if (ctx.getTimerService() != null) {
                Collection<Timer> timers = ctx.getTimerService().getTimers();
                if (timers != null) {
                    for (Timer timer : timers) {
                        timer.cancel();
                    }
                }
            }
        } catch (Exception e) {
            //
        }
    }



  private void createTimer() {
        ctx.getTimerService().createTimer(initialExpiration.getTime(), timeoutInterval, EjbConstants.BILLPAYMENT_EJB);
}

    @Timeout
    public void execute(Timer timer) {
        try {
            LOG.info("[BillingTimerEJB] Ejecutando");
            System.out.println("[BillingTimerEJB] Ejecutando");
            executeBilling();
            stop();
            start();

        } catch (Exception e) {
            LOG.error("Error", e);
        }
    }


    private void executeBilling() throws Exception {

        try {
            //llamar al metodo que actualiza el estado de las remesas
//            BillingsEJBLocal.executeBilling();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void forceExecution() throws Exception {
        LOG.info("Ejecutó forceExecution!!!!!!!!");
        //System.out.println("Ejecutó forceExecution!!!!!!!!");
    }

    public void forceTimeout() throws Exception {
        LOG.info("[TopUpUpdateTimerEJB] Forzando timeout para dentro de 1 minuto");
        //System.out.println("[TopUpUpdateTimerEJB] Forzando timeout para dentro de 1 minuto");
        cancelTimers();
        setTimeoutInterval();
        initialExpiration = Calendar.getInstance();
        initialExpiration.add(Calendar.MINUTE, 3);
        createTimer();
    }

    public Date getNextExecutionDate() {
        if (ctx.getTimerService() != null) {
            Collection<Timer> timers = ctx.getTimerService().getTimers();
            if (timers != null) {
                for (Timer timer : timers) {
                    return timer.getNextTimeout();
                }
            }
        }

        return null;
    }

    public void restart() throws Exception {
        stop();
        start();
        LOG.info("[TopUpUpdateTimerEJB] Reiniciado");
        //System.out.println("[TopUpUpdateTimerEJB] Reiniciado");
    }

    private void setTimeoutInterval() throws Exception {

        initialExpiration = Calendar.getInstance();
        initialExpiration.set(Calendar.HOUR, 4);//Media entre zona horaria de California Y Florida - EN CA 12 am en FL seria las 4 am.
        initialExpiration.set(Calendar.MINUTE, 30);
        initialExpiration.set(Calendar.SECOND, 0);
        initialExpiration.set(Calendar.MILLISECOND, 0);
        initialExpiration.set(Calendar.AM_PM, Calendar.AM);
        Long secondsInDay = 86400L;
        //secondsInDay = secondsInDay * 15;//Cada quince dias
        initialExpiration.add(Calendar.DAY_OF_MONTH, 1);//El timer comienza un día despues que se inicializa.
        timeoutInterval = secondsInDay * 1000L;//Milisegundos
    }

    @SuppressWarnings("unchecked")
    public void start() throws Exception {
        setTimeoutInterval();
        createTimer();
        LOG.info("[TopUpUpdateTimerEJB] Iniciado");
        //System.out.println("TopUpUpdateTimerEJB] Iniciado");
    }

    @SuppressWarnings("unchecked")
    public void stop() throws Exception {
        cancelTimers();
        LOG.info("[TopUpUpdateTimerEJB] Detenido");
        //System.out.println("[TopUpUpdateTimerEJB] Detenido");
    }

    public Long getTimeoutInterval() {
        return timeoutInterval;
    }
}