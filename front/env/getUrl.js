export const getUrl = () => {
    if (process.env.NODE_ENV === 'production') {
        return process.env.NEXT_PUBLIC_PRODUCTION_URL;
    } else {
        return process.env.NEXT_PUBLIC_LOCAL_URL;
    }
};