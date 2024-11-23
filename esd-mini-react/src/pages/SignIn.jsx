import React, { useEffect } from 'react'
import Navbar from '../components/Navbar'
import SigninCard from '../components/SigninCard'

function SignIn() {

  useEffect(
    ()=>{
      fetch('https://jsonplaceholder.typicode.com/todos/1')
      .then(response => response.json())
      .then(json => console.log(json))
    },[]);

  function handleClick(){
    console.log("Clickedddd")
  };
  return (
    <>
      <Navbar />
      <div className="container">
        <SigninCard />
      </div>
    </>
  )
}

export default SignIn;