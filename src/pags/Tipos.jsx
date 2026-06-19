import React, { useState } from 'react';
import { Button } from 'react-bootstrap';


export default function Tipos() {
  const [query, setQuery] = useState('');
  const [data, setData] = useState(null);
  const [error, setError] = useState('');

  async function handleSearch(e) {
    e.preventDefault();
    if (!query.trim()) return;

    setError('');
    setData(null);

    try {
      const res = await fetch(`https://pokeapi.co/api/v2/type/${query.toLowerCase()}`);
      if (!res.ok) throw new Error('Tipo não encontrado');
      const json = await res.json();
      setData(json);
    } catch (err) {
      setError(err.message);
    }
  }

  // Extrai ID do Pokémon da URL para buscar a imagem
  function getPokemonIdFromUrl(url) {
    const parts = url.split('/').filter(Boolean);
    return parts[parts.length - 1];
  }

  return (
    <div style={{ maxWidth: 700, margin: 'auto', fontFamily: 'Arial, sans-serif' }}>
      <h2  style={{color: 'white'}}>Buscar Tipo</h2>
      <form onSubmit={handleSearch} style={{ marginBottom: 20 }}>
        <input
          placeholder="Digite nome ou ID do tipo (ex: fire ou 10)"
          value={query}
          onChange={e => setQuery(e.target.value)}
          style={{ padding: 8, fontSize: 16, width: '70%', marginRight: 10 }}
        />
        <Button type="submit" style={{ padding: '8px 16px', fontSize: 16 }} variant='warning'>
          Buscar
        </Button>
      </form>

      {error && <p style={{ color: 'red' }}>{error}</p>}

      {data && (
        <div>
          <h3 style={{ textTransform: 'capitalize',color: 'white' }}>Tipo: {data.name}</h3>

          <h4 style={{color: 'white'}}>Pokémons deste tipo:</h4>
          <div
            style={{
              display: 'flex',
              flexWrap: 'wrap',
              gap: 15,
              maxHeight: 400,
              overflowY: 'auto',
              border: '1px solid #ccc',
              padding: 10,
              borderRadius: 8,
            }}
          >
            {data.pokemon.map(({ pokemon }) => {
              const id = getPokemonIdFromUrl(pokemon.url);
              const imgUrl = `https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png`;

              return (
                <div
                  key={pokemon.name}
                  style={{
                    width: 100,
                    textAlign: 'center',
                    textTransform: 'capitalize',
                    fontSize: 14,
                    color: 'white',
                  }}
                >
                  <img
                    src={imgUrl}
                    alt={pokemon.name}
                    width={80}
                    height={80}
                    style={{ imageRendering: 'pixelated' }}
                  />
                  <div>{pokemon.name}</div>
                </div>
              );
            })}
          </div>
        </div>
      )}
    </div>
  );
}
