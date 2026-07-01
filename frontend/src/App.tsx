import { useEffect, useState } from "react";

// Definimos la estructura exacta que nos devuelve Java usando una interfaz de TypeScript
interface Solarpanel {
  id: number;
  model: string;
  currentOutput: number;
  status: 'OK' | 'WARNING' | 'FAULT';
  installationDate:string;
  lastUpdate: string;
}

function App() {
     const [solarpanels, setSolarpanels] = useState<Solarpanel[]>([]);
     const [Loading, setLoading] = useState<boolean>(true);

     useEffect(() => {
      console.log("🚀 React: Iniciando petición al backend...");
         // Hacemos la petición al backend de Java para obtener los datos de los paneles solares
        fetch('https://greentech-backend-8rmi.onrender.com/api/panels')
      .then((response) => {
      console.log("📡 React: Estado de la respuesta HTTP:", response.status);
      if (!response.ok) {
        throw new Error(`Error en el servidor: ${response.status}`);
      }
      return response.json();
    })
      .then(data => {
        console.log("📦 React: Datos JSON crudos recibidos desde Java:", data);
        setSolarpanels(data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Error conectando con el cerebro de Java:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div style={{ padding: '40px', fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto' }}>
      <header style={{ borderBottom: '2px solid #2e7d32', paddingBottom: '10px', marginBottom: '30px' }}>
        <h1 style={{ color: '#2e7d32', margin: 0 }}>🌱 GreenTech Dashboard</h1>
        <p style={{ color: '#666', marginTop: '5px' }}>Monitor de Eficiencia Energética en Tiempo Real</p>
      </header>

      {Loading ? (
        <p>Conectando con el servidor...</p>
      ) : (
        <div>
          <h2>Paneles Solares Registrados ({solarpanels.length})</h2>
          
          {solarpanels.length === 0 ? (
            <div style={{ backgroundColor: '#f5f5f5', padding: '20px', borderRadius: '8px', textAlign: 'center' }}>
              <p style={{ color: '#555' }}>No hay paneles solares conectados al sistema todavía.</p>
              <p style={{ fontSize: '14px', color: '#888' }}>El cerebro está listo, pero la base de datos está vacía. ¡Pronto crearemos el primer panel!</p>
            </div>
          ) : (
            <ul style={{ listStyleType: 'none', padding: 0 }}>
  {solarpanels.map(panel => (
    <li key={panel.id} style={{ 
      backgroundColor: '#f9f9f9', 
      margin: '10px 0', 
      padding: '15px', 
      borderRadius: '6px',
      borderLeft: `5px solid ${panel.status === 'OK' ? '#2e7d32' : panel.status === 'WARNING' ? '#f57c00' : '#d32f2f'}`
    }}>
      <strong>{panel.model}</strong> — Rendimiento: {panel.currentOutput} W 
      <span style={{ float: 'right', fontWeight: 'bold' }}>[{panel.status}]</span>
    </li>
  ))}
</ul>
          )}
        </div>
      )}
    </div>
  )
    
}


export default App