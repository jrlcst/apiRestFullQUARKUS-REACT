import icon from "../../assets/img/notification-icon.svg"
import "./styles.css"

function notificationButton() {
    return(
        <div className="dsmeta-red-btn">
        <img src={icon} alt="Notificar" />
      </div>
    )
   }
   
   export default notificationButton