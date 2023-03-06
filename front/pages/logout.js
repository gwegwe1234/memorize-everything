import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Logout() {
    const router = useRouter()

    useEffect(() => {
        localStorage.removeItem("token")
        router.push('/login');
      }, []);

      return <div>Logging out...</div>;
}