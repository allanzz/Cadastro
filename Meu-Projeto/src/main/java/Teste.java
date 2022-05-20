import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.persistence.internal.security.PrivilegedClassForName;

import br.com.allan.Movimentacao;
import br.com.allan.TipoMovimentacao;
import br.com.allan.dao.Dao;
import br.com.allan.modelos.Produto;

public class Teste {

	public static void main(String[] args) throws Exception {
		System.out.println(TipoMovimentacao.ENTRADA.getValue());
		Class<?> classe =  Class.forName("br.com.allan."+TipoMovimentacao.ENTRADA.getValue());
		Movimentacao movimentacao = (Movimentacao) classe.getDeclaredConstructor(null).newInstance();
		System.out.println(movimentacao.toString());
	}
}
