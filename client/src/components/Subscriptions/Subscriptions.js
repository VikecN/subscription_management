import { Pagination } from "@mui/material";
import "./Subscriptions.css"
import { useEffect, useRef, useState } from "react";
import Subscription from "./Subscription/Subscription.js";
import axios from "axios";

var Subscriptions = (props) => {

    const [content, setContent] = useState({content: {content: []}});
    const [currentPage, setCurrentPage] = useState(0);
    
    const isCalledRef = useRef(false);
    useEffect(() => {
        if(!isCalledRef.current) {
            axios.get("http://localhost:9000/api/v1/subscriptions?limit=1&offset=" + currentPage)
            .then(response => (
                setContent(response.data),
                setCurrentPage(response.data.totalPages)
                )
            )
            .catch(error => 
                console.log(error))
            isCalledRef.current = true
        }
    }, [isCalledRef])

    var nextPage = (data) => {
        console.log(data.target.innerText);
        setCurrentPage(parseInt(data.target.innerText));
        isCalledRef.current = false

    }

    return (
    <div className="subscriptions">
        <ul className="subs-list">
            {
                
                content.content.length > 0 && content?.content.map((element, index) => 
                    <li key={index}>
                        <Subscription element={element} />
                        {/* <div>{element.name}</div> */}
                    </li>
                )
            }
        </ul>
        <div className="pagination">
            <Pagination count={currentPage} variant="outlined" color="primary" onChange={(data) => nextPage(data)}/>
        </div>
    </div>);
} 

export default Subscriptions;

