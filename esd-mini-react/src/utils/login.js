import axios from 'axios';
import config from '../config';


export const fetchLogin = async (email, password) => {
    try {
        const response = await axios.post(`${config}/api/v1/login`,{
            email,
            password,
        });
        console.log("==================");
        console.log(response.data);
        // Return the data received from the API
        return response.data; 
    } catch (error) {
        // Throw error to handle it in the component
        throw new Error('Error fetching data: ' + error.message); 
    }
};
