import { FormControlLabel, Switch } from "@mui/material";
import axios from "axios";
import { useState } from "react";

var Subscription = (props) => {

    const [subscription, setSubscription] = useState(props.element);

    const changeStatus = () => {
        axios.put("http://localhost:9000/api/v1/subscriptions/status/" + subscription.id)
        .then(response => 
            setSubscription((prevState) => ({...prevState, active: !subscription.active})))
        .catch(error => 
            console.log(error))
    } 

    return (
        <div className="subscription">
            <h3>{subscription.name}</h3>
            <p>Amount: {subscription.amount} {subscription.currency}</p>
            <a href={subscription.link}>Go to page</a>
            <div>
                <FormControlLabel control={<Switch 
                                            checked={subscription.active} onChange={changeStatus}/>
                                        } label="Active" />
            <hr />
            </div>
        </div>
    );
}

export default Subscription;
