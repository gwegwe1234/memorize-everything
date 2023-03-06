import { useEffect, useState } from "react";

export function getToken() {

    const [token, setToken] = useState('')

    useEffect(() => {
        setToken(localStorage.getItem('token'))
    })

    return token ? token : "";
} 
