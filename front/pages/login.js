import { useState } from 'react';
import Layout from '../components/layout';
import { getUrl } from '../env/getUrl'
import { useRouter } from 'next/router';

export default function Login() {
    const url = getUrl();
    const router = useRouter();
    const [credentials, setCredentials] = useState({ username: '', password: '' });

    const handleInputChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const username = window.btoa(credentials.username)
        const password = window.btoa(credentials.password)

        const endpoint = `${url}/perform_login?username=${username}&password=${password}`

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        }

        await fetch(endpoint, options)
            .then((res) => res.json())
            .then((json) => {
                console.log(json)
                if (json.statusCode == 200) {
                    localStorage.setItem("token", json.data)
                    alert("로그인 성공")
                    router.push("/");
                } else {
                    alert(json.error)
                }
            })
    };

    return (
        <Layout>
            <center>
            <form onSubmit={handleSubmit}>
                <label>
                    <input
                        type="text"
                        name="username"
                        value={credentials.id}
                        onChange={handleInputChange}
                        placeholder="아이디"
                    />
                </label>
                <label>
                    <input
                        type="text"
                        name="password"
                        value={credentials.password}
                        onChange={handleInputChange}
                        placeholder="패스워드"
                    />
                </label>
                <button type="submit">Log in</button>
            </form>
            </center>
        </Layout>

    );
}


