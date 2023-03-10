import Layout from "../../../components/layout";
import Head from 'next/head';
import { useState, useEffect } from 'react'
import { useRouter } from "next/router";
import { getUrl } from "../../../env/getUrl";
import { getToken } from "../../../components/token";
import ProtectedRoute from "../../protectedRoute";

export default function updateBookPost() {
    const [inputValue, setInputValue] = useState("")
    const [textEnable, setTextEnable] = useState(false)
    const [isUpdate, setUpdate] = useState(false)
    const [isLoading, setLoading] = useState(false)
    const [data, setData] = useState(null)

    const router = useRouter();
    const url = getUrl();
    const token = getToken();

    const { id } = router.query;

    useEffect(() => {
        const fetchData = async () => {
            try {
                const options = {
                    method: 'GET',
                    headers: {
                      'Authorization': `Bearer ${token}`,
                    }
                }
                const response = await fetch(`${url}/book/info?id=${id}`, options);
                const res = await response.json();
                setData(res.data);
            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        if (id) {
            fetchData();
        }

    }, [id])

    if (isLoading) return <p>Loading...</p>

    const handleSubmit = async (event) => {
        event.preventDefault()

        if (!isUpdate) {
            setUpdate(true)
            setTextEnable(true)
        } else {
            const action = event.nativeEvent.submitter.name;

            const data = {
                title: event.target.title.value,
                summary: event.target.summary.value,
                category: event.target.category.value,
            }

            const JSONdata = JSON.stringify(data)

            const endpoint = `${url}/book/info`

            if (action === 'update') {
                const options = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': `Bearer ${token}`
                    },
                    body: JSONdata,
                }

                await fetch(endpoint, options)
                    .then((res) => res.json())
                    .then((json) => {
                        if (json.status == 400) {
                            alert(json.message)
                        } else {
                            alert("?????? ??????")
                            router.back();
                        }
                    })

            } else if (action === 'delete') {
                const options = {
                    method: 'DELETE',
                    headers: {
                        'Authorization': `Bearer ${token}`
                    },
                }

                const deleteUrl = endpoint + `?title=${data.title}`

                await fetch(deleteUrl, options)
                    .then((res) => res.json())
                    .then((json) => {
                        if (json.status == 400) {
                            alert(json.message)
                        } else {
                            alert("??????")
                            router.back();
                        }
                    })
            }
        }
    }

    function handleInputChange(event) {
        setInputValue(event.target.value);
    }

    return (
        <div>
            <Layout>
                <Head>
                    <title>??? ?????? ?????????</title>
                </Head>
                {data &&
                    <form onSubmit={handleSubmit}>
                        <h3>??????</h3>
                        <input type="text" id="title" name="title" onChange={handleInputChange} defaultValue={data.title} disabled={!textEnable} required />
                        <br />
                        <hr />
                        <h3>??????</h3>
                        <textarea
                            rows="20" name="summary"
                            id="summary" cols="40"
                            className="ui-autocomplete-input"
                            autoComplete="off"
                            role="textbox"
                            aria-autocomplete="list"
                            aria-haspopup="true" required
                            defaultValue={data.summary}
                            disabled={!textEnable}
                            onChange={handleInputChange}
                        />
                        <br />
                        <hr />
                        <h3>????????????</h3>
                        <input type="text" className="pretty-textarea" id="category" name="category" onChange={handleInputChange}  defaultValue={data.category} disabled={!textEnable} required />
                        <br />
                        <br />
                        <button type="submit" name="update" >{isUpdate ? '??????' : '??????'}</button>
                        <button type="submit" name="delete" > ??????</button>
                    </form>
                }
                {!data && <p>Cout not find book with Id {id} </p>}
                <ProtectedRoute />
            </Layout>
        </div>
    )
}