import { useOktaAuth } from "@okta/okta-react";
import { SpinnerLoading } from "../Utils/SpinnerLoading";

export const Navbar = () => {

    const { oktaAuth, authState } = useOktaAuth();

    if(!authState) {
      return <SpinnerLoading/>
    }

    const handleLogout = async () => oktaAuth.signOut();

    return (
    <nav className="navbar navbar-expand-lg navbar-dark main-color py-3">
      <div className="container-fluid">
        <span className='Navbar-brand'> PerfectoZ </span>
        <button className='navbar-toggler' type='button'
          data-bs-toggle='collapse' data-bs-target='#navbarNavDropDown'
          aria-controls='navbarNavDropDown' aria-expanded='false'
          aria-label='Toggle Navigation'
        > 
          <span className='navbar-toggler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarNavDropDown'>
          <ul className='navbar-nav'>
            <li className='nav-item'>
              <a className='nav-link' href='/home'> Home </a>
            </li>
            <li className='nav-item'>
              <a className='nav-link' href='/search'> Search Books </a>
            </li>
          </ul>
          <ul className='navbar-nav ms-auto'>
            {!authState.isAuthenticated ? 
              <li className='nav-item m-1'>
              <a type='button' className='btn btn-outline-light' href='/login'> Login </a>
              </li>
              :
              <li>
                <button className='btn btn-outline-light' onClick={handleLogout}>Logout</button>
              </li>
            } 
          </ul>
        </div>
      </div>
    </nav>
    )
}