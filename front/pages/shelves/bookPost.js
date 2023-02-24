import Layout from "../../components/layout";
import Head from 'next/head';
import PrettyButton from "../../lib/prettyButton";
import { useRouter } from 'next/router';
import { getUrl } from "../../env/getUrl";

export default function BookPost() {
    const router = useRouter();
    const url = getUrl();
    
    const handleSubmit = async (event) => {
        event.preventDefault()

        const data = {
            title: event.target.title.value,
            summary: event.target.summary.value,
            category: event.target.category.value,
        }

        const JSONdata = JSON.stringify(data)

        const endpoint = `${url}/book/info`

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSONdata,
        }

        await fetch(endpoint, options)
            .then((res) => res.json())
            .then((json) => {
                console.log(json)
                if (json.status == 400) {
                    alert(json.message)
                } else {
                    alert("성공")
                    router.back();
                }

            })
    }

    return (
        <div>
            <Layout>
                <Head>
                    <title>책 등록 페이지</title>
                </Head>
                <form onSubmit={handleSubmit}>
                    <h3>제목</h3>
                    <input type="text" id="title" name="title" required />
                    <br />
                    <hr />
                    <h3>요약</h3>
                    <textarea
                        rows="20" name="summary"
                        id="summary" cols="40"
                        className="ui-autocomplete-input"
                        autoComplete="off"
                        role="textbox"
                        aria-autocomplete="list"
                        aria-haspopup="true" required ></textarea>
                    <br />
                    <hr />
                    <h3>카테고리</h3>
                    <input type="text" className="pretty-textarea" id="category" name="category" required />
                    <br />
                    <br />
                    <PrettyButton type="submit">등록</PrettyButton>
                </form>
            </Layout>
        </div>
    )
}