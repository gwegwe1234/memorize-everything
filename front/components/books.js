import { useState, useEffect } from 'react'
import Link from 'next/link'

function Books() {
  const [data, setData] = useState(null)
  const [isLoading, setLoading] = useState(false)

  useEffect(() => {
    setLoading(true)
    fetch('http://gwegwe1234.synology.me:8080/book')
      .then((res) => res.json())
      .then((json) => {
        if (json.statusCode == 400) {
          alert(json.message)
        } else {
          setData(json.data)
          setLoading(false)
        }
        
      })
  }, [])

  if (isLoading) return <p>Loading...</p>
  if (!data) return <p>No profile data</p>

  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            {/* <th>책 내용</th>
            <th>카테고리</th> */}
          </tr>
        </thead>
        <tbody>
          {data.map(({ id, title, summary, category }) => (
            <tr>
              <td>{id}</td>
              <td>
                <Link href={{
                    pathname: '/shelves/updateBookPost',
                    query: { id: id },
                  }}
                    as={`/shelves/updateBookPost/${id}`}>
                    {title}
                </Link>
              </td>
              {/* <td>{summary}</td>
              <td>{category}</td> */}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Books;