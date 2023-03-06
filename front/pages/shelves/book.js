import Link from 'next/link';
import Head from 'next/head';
import Layout from '../../components/layout';
import Books from '../../components/books';
import ProtectedRoute from '../protectedRoute';

export default function Book() {

    return (
        <Layout>
            <Head>
                <title>기록용 책장</title>
            </Head>
            <center><h1>읽은 책 목록</h1></center>
            <Link href="/shelves/bookPost">
                <button id="right-button">책 추가</button>
            </Link>
            <br/>
            <br/>
            <Books></Books>
            <ProtectedRoute />
        </Layout>
    );
}