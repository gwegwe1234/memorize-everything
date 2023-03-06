import { useEffect } from 'react';
import { useRouter } from 'next/router';

const ProtectedRoute = () => {
    const router = useRouter();
  
    const checkToken = () => {
      const hasToken = Boolean(localStorage.getItem('token'));
      if (!hasToken) {
        router.push('/login');
      }
    };
  
    useEffect(() => {
      checkToken();
    }, []);
  };
  
  export default ProtectedRoute;
  
  
  
  
  
  